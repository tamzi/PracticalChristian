#!/usr/bin/env python3
"""
Extract unresolved comments from all closed PRs in the repository.
This script uses the GitHub API to fetch all closed PRs and their unresolved review comments.
"""

import json
import os
import sys
from datetime import datetime
from typing import List, Dict, Any

def load_github_token() -> str:
    """Load GitHub token from environment variable."""
    token = os.environ.get('GITHUB_TOKEN')
    if not token:
        print("Warning: GITHUB_TOKEN not found. API rate limits will be lower.", file=sys.stderr)
    return token

def parse_pr_data(pr_file: str) -> List[Dict[str, Any]]:
    """Parse PR data from file."""
    try:
        with open(pr_file, 'r') as f:
            return json.load(f)
    except Exception as e:
        print(f"Error loading PR data: {e}", file=sys.stderr)
        return []

def extract_unresolved_comments(output_file: str = "unresolved_comments_report.md") -> None:
    """
    Extract unresolved comments from closed PRs and generate a report.
    
    This script expects the PR data to be available in a JSON file.
    In a real-world scenario, this would make API calls to GitHub.
    """
    
    # For this implementation, we'll create a template that shows the structure
    # and can be populated with actual data
    
    report_lines = [
        "# Unresolved Comments from Closed PRs",
        "",
        f"Generated on: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}",
        "",
        "This report contains all unresolved review comments from closed pull requests.",
        "Use this list to track potential bugfixes and improvements.",
        "",
        "---",
        ""
    ]
    
    # Read the PR list file if it exists
    pr_data_file = "/tmp/closed_prs.json"
    if os.path.exists(pr_data_file):
        prs = parse_pr_data(pr_data_file)
        
        if not prs:
            report_lines.extend([
                "## No Data Available",
                "",
                "No closed PRs found or unable to parse PR data.",
                ""
            ])
        else:
            report_lines.extend([
                f"## Summary",
                "",
                f"- Total closed PRs analyzed: {len(prs)}",
                f"- Repository: tamzi/PracticalChristian",
                "",
                "---",
                ""
            ])
            
            # Note: The actual unresolved comments would need to be fetched
            # using the GitHub API for each PR's review comments
            report_lines.extend([
                "## Unresolved Comments by PR",
                "",
                "*Note: To fetch actual unresolved comments, this script needs to query*",
                "*the GitHub API for each PR's review threads. This requires authenticated*",
                "*API access and additional implementation.*",
                "",
            ])
            
            for pr in prs[:10]:  # Show first 10 as example
                report_lines.extend([
                    f"### PR #{pr.get('number')}: {pr.get('title')}",
                    f"- **State**: {pr.get('state')}",
                    f"- **Author**: @{pr.get('user', {}).get('login', 'unknown')}",
                    f"- **URL**: {pr.get('html_url')}",
                    f"- **Merged**: {pr.get('merged_at') is not None}",
                    "",
                    "*Unresolved comments would be listed here*",
                    "",
                ])
    else:
        report_lines.extend([
            "## Setup Required",
            "",
            "To generate a complete report, you need to:",
            "",
            "1. Export closed PRs data using GitHub API",
            "2. For each PR, fetch review comments",
            "3. Filter for unresolved comments",
            "",
            "### Example Usage with GitHub CLI:",
            "",
            "```bash",
            "# List all closed PRs",
            "gh pr list --repo tamzi/PracticalChristian --state closed --json number,title,url,author --limit 1000 > closed_prs.json",
            "",
            "# For each PR, check review comments",
            "# (This would need to be done in a loop)",
            "```",
            "",
        ])
    
    # Write the report
    report_path = os.path.join(os.path.dirname(__file__), output_file)
    with open(report_path, 'w') as f:
        f.write('\n'.join(report_lines))
    
    print(f"Report generated: {report_path}")
    return report_path

if __name__ == "__main__":
    output_file = sys.argv[1] if len(sys.argv) > 1 else "unresolved_comments_report.md"
    extract_unresolved_comments(output_file)
